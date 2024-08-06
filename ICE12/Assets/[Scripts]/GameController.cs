using TMPro;
using UnityEngine;

public class GameController : MonoBehaviour
{
    [Header("Text Properties")]
    public TMP_Text livesLabel;
    public TMP_Text scoreLabel;

    private int m_lives;
    private int m_score;

    // Start is called once before the first execution of Update after the MonoBehaviour is created
    void Start()
    {
        SetLivesLabel(5);
        SetScoreLabel(0);
    }

    public void AddScore(int score)
    {
        m_score += score;
        SetScoreLabel(m_score);
    }

    public void LoseLife()
    {
        m_lives -= 1;
        SetLivesLabel(m_lives);
    }

    public void SetLivesLabel(int lives)
    {
        livesLabel.text = "Lives: " + lives;
        m_lives = lives;
    }

    public void SetScoreLabel(int score)
    {
        scoreLabel.text = "Score: " + score;
        m_score = score;
    }

    public int GetLives()
    {
        return m_lives;
    }

    public int GetScore()
    {
        return m_score;
    }
}
