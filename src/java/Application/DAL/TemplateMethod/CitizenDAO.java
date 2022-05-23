package Application.DAL.TemplateMethod;

import Application.DAL.TemplateMethod.AbstractDAO_TP;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CitizenDAO
{
    public final AbstractDAO_TP<Object, Object> read = new AbstractDAO_TP<>() {
        @Override
        protected Object execute(PreparedStatement statement, Object input) {
            return null;
        }

        @Override
        protected String getSQLStatement() {
            return null;
        }

    };

    public final AbstractDAO_TP<Object, Object> create = new AbstractDAO_TP<>() {
        @Override
        protected Object execute(PreparedStatement statement, Object input) {
            return null;
        }

        @Override
        protected String getSQLStatement() {
            return null;
        }

    };

}
