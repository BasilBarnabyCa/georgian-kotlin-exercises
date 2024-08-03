using UnityEngine;

public class CloudController : MonoBehaviour
{
    [Header("Movement Properties")]
    public Boundary verticalBoundary;
    public Boundary horizontalBoundary;
    public Boundary offscreenBoundary;

    [Range(5.0f, 10.0f)]
    public float minVerticalSpeed;

    [Range(5.0f, 10.0f)]
    public float maxVerticalSpeed;

    public float verticalSpeed;

    [Range(-2.0f, 2.0f)]
    public float minHorizontalSpeed;

    [Range(-2.0f, 2.0f)]
    public float maxHorizontalSpeed;
    
    public float horizontalSpeed;


    //create boudnary class for min max
    // create super class for all


    // Start is called once before the first execution of Update after the MonoBehaviour is created
    void Start()
    {
        ResetGameObject();
    }

    // Update is called once per frame
    void Update()
    {
        Move();
        CheckBounds();
    }

    void ResetGameObject()
    {
        var randomXPosition = Random.Range(horizontalBoundary.min, horizontalBoundary.max);
        var randomYPosition = Random.Range(offscreenBoundary.min, offscreenBoundary.max);
        horizontalSpeed = Random.Range(minHorizontalSpeed, maxHorizontalSpeed);
        verticalSpeed = Random.Range(minVerticalSpeed, maxVerticalSpeed);
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
