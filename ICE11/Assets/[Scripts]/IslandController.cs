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
        Move(0.0f, verticalSpeed, 0.0f);
        CheckBounds(horizontalBoundary, verticalBoundary);
    }
}
