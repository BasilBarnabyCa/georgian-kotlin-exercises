using UnityEngine;

public class CloudController : MovementController
{
    [Header("Position Properties")]
    public Boundary verticalBoundary;
    public Boundary horizontalBoundary;
    public Boundary offscreenBoundary;

    [Header("Speed Properties")]
    private float horizontalSpeed;
    private float verticalSpeed;

    // Instanciate boundary with min and max values instead of using Range annotation
    public Boundary verticalSpeedLimit = new Boundary(5.0f, 10.0f);
    public Boundary horizontalSpeedLimit = new Boundary(-2.0f, 2.0f);

    void Start()
    {
        CheckBounds();
        horizontalSpeed = Random.Range(horizontalSpeedLimit.min, horizontalSpeedLimit.max);
        verticalSpeed = Random.Range(verticalSpeedLimit.min, verticalSpeedLimit.max);
    }

    void Update()
    {
        Move(horizontalSpeed, verticalSpeed, 0.0f);
        CheckBounds();
    }

    public override void CheckBounds()
    {
        if (transform.position.y <= verticalBoundary.min)
        {
            var randomXPosition = Random.Range(horizontalBoundary.min, horizontalBoundary.max);
            var randomYPosition = Random.Range(offscreenBoundary.min, offscreenBoundary.max);

            transform.position = new Vector3(randomXPosition, randomYPosition, 0.0f);
        }
    }
}
