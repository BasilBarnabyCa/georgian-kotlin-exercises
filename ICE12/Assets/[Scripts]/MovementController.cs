using UnityEngine;

/// <summary>
/// Abstract base class that provides a framework for movement and boundary checking for derived classes.
/// </summary>
public abstract class MovementController : MonoBehaviour
{
    /// <summary>
    /// Abstract method to be implemented by derived classes for checking and enforcing boundary constraints.
    /// </summary>
    public abstract void CheckBounds();

    /// <summary>
    /// Moves the game object by updating its position based on the specified speed values.
    /// </summary>
    /// <param name="xPosition">The horizontal speed.</param>
    /// <param name="yPosition">The vertical speed.</param>
    /// <returns>Does not return a value.</returns>
    public virtual void Move(float xPosition, float yPosition)
    {
        transform.position += new Vector3(-xPosition * Time.deltaTime, -yPosition * Time.deltaTime, 0.0f);
    }

    /// <summary>
    /// Sets the game object's position to the specified coordinates.
    /// </summary>
    /// <param name="xPosition">The x-coordinate of the new position.</param>
    /// <param name="yPosition">The y-coordinate of the new position.</param>
    /// <returns>Does not return a value.</returns>
    public void SetPosition(float xPosition, float yPosition)
    {
        transform.position = new Vector3(xPosition, yPosition, 0.0f);
    }
}
