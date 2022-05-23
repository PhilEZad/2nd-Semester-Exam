package Application.Utility;

import javafx.beans.binding.StringBinding;
import javafx.beans.binding.StringExpression;
import javafx.beans.property.Property;
import javafx.beans.property.StringProperty;
import javafx.util.StringConverter;

public class Bind {
    public static  <T> void oneWay(Property<T> lhs, Property<T> rhs)
    {
        lhs.unbind();
        lhs.unbindBidirectional(rhs);
        lhs.bind(rhs);
    }

    public static void oneWay(StringProperty lhs, StringBinding rhs)
    {
        lhs.unbind();
        lhs.unbindBidirectional(rhs);
        lhs.bind(rhs);
    }

    public static  <T> void twoWay(StringProperty lhs, Property<T> last, Property<T> current, StringConverter<T> converter)
    {
        lhs.unbind();
        lhs.unbindBidirectional(last);
        lhs.bindBidirectional(current, converter);
    }

    public static  <T> void twoWay(StringProperty lhs, StringProperty last, StringProperty current)
    {
        lhs.unbind();
        lhs.unbindBidirectional(last);
        lhs.bindBidirectional(current);
    }


}
