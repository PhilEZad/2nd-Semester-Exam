package Application.Utility.StateMachine;

public interface IState
{
    Object getState();

    void disable();
    void enable();
}
