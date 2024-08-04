using UnityEngine;

public class CloudController : MovementController
{
    [Header("Position Properties")]
    public Boundary verticalBoundary;
    public Boundary horizontalBoundary;
    public Boundary offscreenBoundary;

    // Instanciate boundary with min and max values instead of using Range annotation
    public Boundary verticalSpeedLimit = new Boundary(5.0f, 10.0f);
    public Boundary horizontalSpeedLimit = new Boundary(-2.0f, 2.0f);
    

    void Start()
    {
        ResetGameObject(horizontalBoundary, offscreenBoundary, horizontalSpeedLimit, verticalSpeedLimit);
        float horizontalSpeed = Random.Range(horizontalSpeedLimit.min, horizontalSpeedLimit.max);
        float verticalSpeed = Random.Range(verticalSpeedLimit.min, verticalSpeedLimit.max);
    }

    void Update()
    {
        Move(horizontalSpeed, verticalSpeed);
        CheckBounds(horizontalBoundary, verticalBoundary, offscreenBoundary, horizontalSpeedLimit, verticalSpeedLimit);
    }

    void Move(float horizontalSpeed, float verticalSpeed)
    {
        transform.position += new Vector3(-horizontalSpeed * Time.deltaTime, -verticalSpeed * Time.deltaTime, 0.0f);
    }
}
