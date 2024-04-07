package nextstep.courses.domain.session;

import nextstep.courses.domain.BaseTime;

public class Session extends BaseTime {
  private Long id;
  private SessionPeriod sessionPeriod;
  private SessionInfo sessionInfo;
  private CoverImage coverImage;

  private Session() {
  }

  public Session(Long id, SessionPeriod sessionPeriod, SessionInfo sessionInfo, CoverImage coverImage) {
    this.id = id;
    this.sessionPeriod = sessionPeriod;
    this.sessionInfo = sessionInfo;
    this.coverImage = coverImage;
  }

  public Long getId() {
    return id;
  }

  public SessionStatus getSessionStatus() {
    return sessionInfo.getSessionStatus();
  }

  public SessionPeriod getSessionPeriod() {
    return sessionPeriod;
  }

  public boolean checkApplyPossible() {
    return sessionInfo.checkApplyPossible();
  }
}
