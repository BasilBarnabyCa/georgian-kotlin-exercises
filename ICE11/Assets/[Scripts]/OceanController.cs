using UnityEngine;

public class OceanController : MovementController
{
    [Header("Position Properties")]
    public Boundary boundary;

    [Header("Speed Properties")]
    public float verticalSpeed;

    void Start()
    {
        ResetGameObject(boundary.max);
    }

    void Update()
    {
        Move(verticalSpeed);
        CheckBounds(boundary);
    }
}
