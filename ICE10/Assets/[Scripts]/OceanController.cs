using UnityEngine;

public class OceanController : MonoBehaviour
{
    public float max;
    public float min;
    public float horizontalSpeed;


    // Start is called once before the first execution of Update after the MonoBehaviour is created
    void Start()
    {
        resetGameObject();
    }

    // Update is called once per frame
    void Update()
    {
        Move();
        CheckBounds();
    }

    void resetGameObject()
    {
        transform.position = new Vector3(0.0f, max, 0.0f);
    }

    void Move()
    {
        transform.position += new Vector3(0.0f, -horizontalSpeed * Time.deltaTime, 0.0f);
    }

    void CheckBounds()
    {
        if (transform.position.y <= min) { 
            resetGameObject();
        }
    }
}
