using UnityEngine;

public abstract class MovementController : MonoBehaviour
{
    public abstract void CheckBounds();

    public void ApplyBounds(Boundary boundary) 
    {
        if (transform.position.y <= boundary.min)
        {
            ResetGameObject(boundary.max);
        }
    }

    public void ApplyBounds(Boundary horizontalBoundary, Boundary verticalBoundary)
    {
        if (transform.position.y <= verticalBoundary.min)
        {
            ResetGameObject(horizontalBoundary, verticalBoundary.max);
        }
    }

    public void ApplyBounds(Boundary horizontalBoundary,  Boundary verticalBoundary, Boundary offscreenBoundary)
    {
        if (transform.position.y <= verticalBoundary.min)
        {
            ResetGameObject(horizontalBoundary, offscreenBoundary);
        }
    }

    public void ResetGameObject(float yPosition) 
    {
        SetPosition(0.0f, yPosition, 0.0f);
    }

    public void ResetGameObject(Boundary horizontalBoundary, float yPosition)
    {
        var randomXPosition = Random.Range(horizontalBoundary.min, horizontalBoundary.max);
        SetPosition(randomXPosition, yPosition, 0.0f);
    }

    public void ResetGameObject(Boundary horizontalBoundary, Boundary offscreenBoundary)
    {
        var randomXPosition = Random.Range(horizontalBoundary.min, horizontalBoundary.max);
        var randomYPosition = Random.Range(offscreenBoundary.min, offscreenBoundary.max);

        transform.position = new Vector3(randomXPosition, randomYPosition, 0.0f);
    }

    public virtual void Move(float xPosition, float yPosition, float zPosition)
    {
        transform.position += new Vector3(-xPosition * Time.deltaTime, -yPosition * Time.deltaTime, zPosition);
    }

    public void SetPosition(float xPosition, float yPosition, float zPosition)
    {
        transform.position = new Vector3(xPosition, yPosition, zPosition);
    }
}
