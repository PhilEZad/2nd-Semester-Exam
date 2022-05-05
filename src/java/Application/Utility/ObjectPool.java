package Application.Utility;

import java.util.Enumeration;
import java.util.Hashtable;


/**
 * @author SourceMaking.com
 * @param <T>
 */

public abstract class ObjectPool<T> {
    private long expirationTime;

    private Hashtable<T, Long> locked, unlocked;

    public ObjectPool() {
        expirationTime = 30000; // 30 seconds
        locked = new Hashtable<T, Long>();
        unlocked = new Hashtable<T, Long>();
    }

    /**
     * Add an object to the pool.
     * @return
     */
    protected abstract T create();

    /**
     * Indicate whether an object is expunged from the pool.
     */
    public abstract boolean validate(T o);

    /**
     * Remove an object from the pool.
     * @param o
     */
    public abstract void expire(T o);

    /**
     * Borrow an object from the pool.
     * @return
     */
    public synchronized T checkOut() {
        long now = System.currentTimeMillis();
        T t;
        if (unlocked.size() > 0) {
            Enumeration<T> e = unlocked.keys();
            while (e.hasMoreElements()) {
                t = e.nextElement();
                if ((now - unlocked.get(t)) > expirationTime) {
                    // object has expired
                    unlocked.remove(t);
                    expire(t);
                    t = null;
                } else {
                    if (validate(t)) {
                        unlocked.remove(t);
                        locked.put(t, now);
                        return (t);
                    } else {
                        // object failed validation
                        unlocked.remove(t);
                        expire(t);
                        t = null;
                    }
                }
            }
        }
        // no objects available, create a new one
        t = create();
        locked.put(t, now);
        return (t);
    }

    /**
     * Return an object to the pool.
     * @param t
     */
    public synchronized void checkIn(T t) {
        locked.remove(t);
        unlocked.put(t, System.currentTimeMillis());
    }
}
