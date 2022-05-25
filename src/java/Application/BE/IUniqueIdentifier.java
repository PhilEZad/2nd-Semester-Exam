package Application.BE;

public interface IUniqueIdentifier<ID_TYPE>
{
    ID_TYPE getID();

    void setID(ID_TYPE id);
}
