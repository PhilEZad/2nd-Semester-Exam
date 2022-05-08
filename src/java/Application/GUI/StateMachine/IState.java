package Application.GUI.StateMachine;

public interface IState
{
    Object getState();

    void disable();
    void enable();
}
