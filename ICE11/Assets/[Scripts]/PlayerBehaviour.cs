using UnityEngine;

public class PlayerBehaviour : MonoBehaviour
{
    public float max;
    public float min;
    public float horizontalSpeed;
    // Start is called once before the first execution of Update after the MonoBehaviour is created
    void Start()
    {
        
    }

    // Update is called once per frame
    void Update()
    {
        Move();
        CheckBounds();
    }

    void Move()
    {
        var x = Input.GetAxisRaw("Horizontal") * horizontalSpeed * Time.deltaTime;
        transform.position += new Vector3(x, 0.0f, 0.0f);
    }

    void CheckBounds()
    {
        if (transform.position.x <= min)
        {
            transform.position = new Vector3(min, transform.position.y, 0.0f);
        }
        else if (transform.position.x >= max) {
            transform.position = new Vector3(max, transform.position.y, 0.0f);
        }
    }
}
