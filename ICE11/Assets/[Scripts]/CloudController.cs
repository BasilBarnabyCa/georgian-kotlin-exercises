using UnityEngine;

public class CloudController : MonoBehaviour
{
    [Header("Movement Properties")]
    public float minVertical;
    public float maxVertical;
    public float minHorizontal;
    public float maxHorizontal;
    public float minOffscreenVertical;
    public float maxOffScreenVertical;

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
        var randomXPosition = Random.Range(minHorizontal, maxHorizontal);
        var randomYPosition = Random.Range(minOffscreenVertical, minOffscreenVertical);
        horizontalSpeed = Random.Range(minHorizontalSpeed, maxHorizontalSpeed);
        verticalSpeed = Random.Range(minVerticalSpeed, maxVerticalSpeed);
        transform.position = new Vector3(randomXPosition, randomYPosition, 0.0f);
    }

    void Move()
    {
        transform.position += new Vector3(horizontalSpeed * Time.deltaTime, -verticalSpeed * Time.deltaTime, 0.0f);
    }

    void CheckBounds()
    {
        if (transform.position.y <= minHorizontal)
        {
            ResetGameObject();
        }
    }
}
