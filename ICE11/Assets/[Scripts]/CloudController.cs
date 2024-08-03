using UnityEngine;

public class CloudController : MonoBehaviour
{
    [Header("Position Properties")]
    public Boundary verticalBoundary;
    public Boundary horizontalBoundary;
    public Boundary offscreenBoundary;

    [Header("Speed Properties")]
    public float verticalSpeed;
    public float horizontalSpeed;

    // Instanciate boundary with min and max values instead of using Range annotation
    public Boundary verticalSpeedLimit = new Boundary(5.0f, 10.0f);
    public Boundary horizontalSpeedLimit = new Boundary(-2.0f, 2.0f);

    void Start()
    {
        ResetGameObject();
    }

    void Update()
    {
        Move();
        CheckBounds();
    }

    void ResetGameObject()
    {
        var randomXPosition = Random.Range(horizontalBoundary.min, horizontalBoundary.max);
        var randomYPosition = Random.Range(offscreenBoundary.min, offscreenBoundary.max);

        horizontalSpeed = Random.Range(horizontalSpeedLimit.min, horizontalSpeedLimit.max);
        verticalSpeed = Random.Range(verticalSpeedLimit.min, verticalSpeedLimit.max);

        transform.position = new Vector3(randomXPosition, randomYPosition, 0.0f);
    }

    void Move()
    {
        transform.position += new Vector3(-horizontalSpeed * Time.deltaTime, -verticalSpeed * Time.deltaTime, 0.0f);
    }

    void CheckBounds()
    {
        if (transform.position.y <= verticalBoundary.min)
        {
            ResetGameObject();
        }
    }
}
