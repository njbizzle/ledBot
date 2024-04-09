package frc.robot.ledTools.finals;

public final class StripConstraints {
  
  private final int m_startIndex, m_count;

  public StripConstraints(StripConstraints other) {
    m_startIndex = other.getStartIndex();
    m_count = other.getCount();
  }

  public StripConstraints(int startIndex, int count) {
    m_startIndex = startIndex;
    m_count = count;
  }

  public int getStartIndex() {
    return m_startIndex;
  }

  public int getCount() {
    return m_count;
  }
}
