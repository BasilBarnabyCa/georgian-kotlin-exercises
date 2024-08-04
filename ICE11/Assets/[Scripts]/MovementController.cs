using UnityEngine;

public class MovementController : MonoBehaviour
{
    protected virtual void CheckBounds(Boundary boundary) 
    {
        if (transform.position.y <= boundary.min)
        {
            ResetGameObject(boundary.max);
        }
    }

    protected virtual void CheckBounds(Boundary horizontalBoundary, Boundary verticalBoundary)
    {
        if (transform.position.y <= verticalBoundary.min)
        {
            ResetGameObject(horizontalBoundary, verticalBoundary.max);
        }
    }

    protected virtual void CheckBounds(Boundary horizontalBoundary,  Boundary verticalBoundary, Boundary offscreenBoundary)
    {
        if (transform.position.y <= verticalBoundary.min)
        {
            ResetGameObject(horizontalBoundary, offscreenBoundary);
        }
    }

    protected virtual void ResetGameObject(float yPosition) 
    {
        SetPosition(0.0f, yPosition, 0.0f);
    }

    protected virtual void ResetGameObject(Boundary horizontalBoundary, float yPosition)
    {
        var randomXPosition = Random.Range(horizontalBoundary.min, horizontalBoundary.max);
        SetPosition(randomXPosition, yPosition, 0.0f);
    }

    protected virtual void ResetGameObject(Boundary horizontalBoundary, Boundary offscreenBoundary)
    {
        var randomXPosition = Random.Range(horizontalBoundary.min, horizontalBoundary.max);
        var randomYPosition = Random.Range(offscreenBoundary.min, offscreenBoundary.max);

        transform.position = new Vector3(randomXPosition, randomYPosition, 0.0f);
    }

    protected virtual void Move(float xPosition, float yPosition, float zPosition)
    {
        transform.position += new Vector3(-xPosition * Time.deltaTime, -yPosition * Time.deltaTime, zPosition);
    }

    public void SetPosition(float xPosition, float yPosition, float zPosition)
    {
        transform.position = new Vector3(xPosition, yPosition, zPosition);
    }
}
