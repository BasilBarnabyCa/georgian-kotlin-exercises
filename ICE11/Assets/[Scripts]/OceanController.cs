using UnityEngine;

public class OceanController : MovementController
{
    [Header("Position Properties")]
    public Boundary boundary;

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
        ApplyBounds(boundary);
    }
}
