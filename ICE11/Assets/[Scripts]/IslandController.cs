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
        CheckBounds();
    }

    void Update()
    {
        Move(0.0f, verticalSpeed, 0.0f);
        CheckBounds();
    }

    public override void CheckBounds()
    {
        ApplyBounds(horizontalBoundary, verticalBoundary);
    }
}
