using UnityEngine;
using UnityEngine.SceneManagement;
using UnityEngine.UI;

public class UIController : MonoBehaviour
{
    public Button sceneButton;

    public void OnSceneButton_Pressed()
    {
        SceneManager.LoadScene("Main");
    }
}
