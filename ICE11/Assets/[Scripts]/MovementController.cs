using UnityEngine;

public abstract class MovementController : MonoBehaviour
{
    public abstract void CheckBounds();

    public virtual void Move(float xPosition, float yPosition, float zPosition)
    {
        transform.position += new Vector3(-xPosition * Time.deltaTime, -yPosition * Time.deltaTime, zPosition);
    }

    public void SetPosition(float xPosition, float yPosition, float zPosition)
    {
        transform.position = new Vector3(xPosition, yPosition, zPosition);
    }
}
