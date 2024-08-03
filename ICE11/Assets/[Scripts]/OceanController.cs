using UnityEngine;

public class OceanController : MovementController
{
    [Header("Position Properties")]
    public Boundary boundary;

    void Start()
    {
        ResetGameObject(boundary.max);
    }

    void Update()
    {
        Move();
        CheckBounds(boundary);
    }
}
