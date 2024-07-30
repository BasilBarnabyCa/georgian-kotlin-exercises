using UnityEngine;
using UnityEngine.AdaptivePerformance.VisualScripting;

public class PlayerBehaviour : MonoBehaviour
{
    [Header("Movement Properties")]
    public float max;
    public float min;
    public float horizontalSpeed;
    public float verticalPosition;

    [Header("Audio Properties")]
    public AudioSource yaySound;
    public AudioSource thunderSound;

    // Start is called once before the first execution of Update after the MonoBehaviour is created
    void Start()
    {
        transform.position = new Vector3(0.0f, verticalPosition, 0.0f);
    }

    // Update is called once per frame
    void Update()
    {
        Move();
        CheckBounds();
    }

    void Move()
    {
        /**
        var x = Input.GetAxisRaw("Horizontal") * horizontalSpeed * Time.deltaTime;
        transform.position += new Vector3(x, 0.0f, 0.0f);
        */

        if (Input.touchCount > 0)
        {
            var x = Input.touches[0].position.x;
            var horizontalPosition = Camera.main.ScreenToWorldPoint(new Vector3(x, 0.0f, 0.0f)).x;
            // print(horizontalPosition);

            transform.position = new Vector3(horizontalPosition, verticalPosition, 0.0f);
        }
           
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

    void OnTriggerEnter2D(Collider2D collision)
    {
        if (collision.gameObject.CompareTag("Island"))
        {
            yaySound.Play();
        }
        else if(collision.gameObject.CompareTag("Cloud")) 
        { 
            thunderSound.Play();
        }
        
    }
}
