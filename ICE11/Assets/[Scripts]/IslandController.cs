using UnityEngine;

public class IslandController : MovementController
{
    [Header("Position Properties")]
    public Boundary verticalBoundary;
    public Boundary horizontalBoundary;

    void Start()
    {
        ResetGameObject(horizontalBoundary, verticalBoundary.max);
    }

    void Update()
    {
        Move();
        CheckBounds(verticalBoundary);
    }
}
