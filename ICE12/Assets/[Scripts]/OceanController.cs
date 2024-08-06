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
        Move(0.0f, verticalSpeed);
        CheckBounds();
    }

    public override void CheckBounds()
    {
        if (transform.position.y <= boundary.min)
        {
            SetPosition(0.0f, boundary.max);
        }
    }
}
