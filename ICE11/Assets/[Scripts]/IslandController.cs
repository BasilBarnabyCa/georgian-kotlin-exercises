using UnityEngine;

public class IslandController : MonoBehaviour
{
    [Header("Movement Properties")]
    public Boundary verticalBoundary;
    public Boundary horizontalBoundary;
    /*public float minVertical;
    public float maxVertical;
    public float minHorizontal;
    public float maxHorizontal;*/
    public float verticalSpeed;


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
        transform.position = new Vector3(randomXPosition, verticalBoundary.max, 0.0f);
    }

    void Move()
    {
        transform.position += new Vector3(0.0f, -verticalSpeed * Time.deltaTime, 0.0f);
    }

    void CheckBounds()
    {
        if (transform.position.y <= verticalBoundary.min)
        {
            ResetGameObject();
        }
    }
}
