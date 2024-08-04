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
        Move(0.0f, verticalSpeed);
        CheckBounds();
    }

    public override void CheckBounds()
    {
        if (transform.position.y <= verticalBoundary.min)
        {
            var randomXPosition = Random.Range(horizontalBoundary.min, horizontalBoundary.max);
            SetPosition(randomXPosition, verticalBoundary.max);
        }
    }
}
