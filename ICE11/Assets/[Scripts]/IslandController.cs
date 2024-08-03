using UnityEngine;

public class IslandController : MonoBehaviour
{
    [Header("Movement Properties")]
    public Boundary verticalBoundary;
    public Boundary horizontalBoundary;
    public float verticalSpeed;

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
