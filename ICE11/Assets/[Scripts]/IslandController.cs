using UnityEngine;

public class IslandController : MovementController
{
    [Header("Position Properties")]
    public Boundary verticalBoundary;
    public Boundary horizontalBoundary;

    [Header("Speed Properties")]
    public float verticalSpeed;

    void Start()
    {
        ResetGameObject(horizontalBoundary, verticalBoundary.max);
    }

    void Update()
    {
        Move(verticalSpeed);
        CheckBounds(horizontalBoundary, verticalBoundary);
    }
}
