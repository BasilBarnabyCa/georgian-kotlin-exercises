using UnityEngine;

public class MovementController : MonoBehaviour
{
    [Header("Speed Properties")]
    public float verticalSpeed;
    public float horizontalSpeed;

    //protected virtual void Start()
    //{
    //    ResetGameObject();
    //}

    //protected virtual void Update()
    //{
    //    Move();
    //    CheckBounds();
    //}

    protected virtual void Move() 
    {
        transform.position += new Vector3(0.0f, -verticalSpeed * Time.deltaTime, 0.0f);
    }

    protected virtual void CheckBounds() { }

    protected virtual void CheckBounds(Boundary boundary) 
    {
        if (transform.position.y <= boundary.min)
        {
            ResetGameObject(boundary.max);
        }
    }

    protected virtual void ResetGameObject() { }

    protected virtual void ResetGameObject(float yPosition) 
    {
        SetPosition(0.0f, yPosition, 0.0f);
    }

    protected void SetPosition(float xPosition, float yPosition, float zPosition)
    {
        transform.position = new Vector3(xPosition, yPosition, zPosition);
    }
}
