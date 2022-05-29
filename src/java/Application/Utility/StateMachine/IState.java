package Application.Utility.StateMachine;

/**
 * @author Rasmus Sandbæk
 * @author Mads Mandahl-Barth
 * */
public interface IState
{
    Object getState();

    void disable();
    void enable();
}
