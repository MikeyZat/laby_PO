package agh.cs.lab1;

public interface IPositionChangeSubject {
    void addObserver(IPositionChangeObserver observer);
    void removeObserver(IPositionChangeObserver observer);
}
