package com.company.project.module.sys.service.impl;

import com.company.project.module.sys.model.SysUserOnline;
import com.company.project.module.sys.service.SysSessionService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Shiro Session 对象管理
 * @author Chen
 * @created 2019-05-22-14:01.
 */
@Service
public class SysSessionServiceImpl implements SysSessionService {
    @Override
    public List<SysUserOnline> list() {
        return null;
    }

    @Override
    public void forceLogout(String id) {

    }
   /* @Autowired
    private SessionDAO sessionDAO;

    @Autowired
    ObjectMapper mapper;

    @Override
    public List<SysUserOnline> list() {
        List<SysUserOnline> list = new ArrayList<>();
        Collection<Session> sessions = sessionDAO.getActiveSessions();
        for (Session session : sessions) {
            SysUserOnline userOnline = new SysUserOnline();
            SysUser user;
            SimplePrincipalCollection principalCollection;
            if (session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY) == null) {
                continue;
            } else {
                principalCollection = (SimplePrincipalCollection) session
                        .getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
                user = (SysUser) principalCollection.getPrimaryPrincipal();
                userOnline.setUsername(user.getUserName());
                userOnline.setUserId(user.getId().toString());
            }
            userOnline.setId((String) session.getId());
            userOnline.setHost(session.getHost());
            userOnline.setStartTimestamp(session.getStartTimestamp());
            userOnline.setLastAccessTime(session.getLastAccessTime());
            long timeout = session.getTimeout();
            userOnline.setStatus(timeout == 0L ? "0" : "1");
            String address = AddressUtils.getCityInfo(userOnline.getHost());
            userOnline.setLocation(address);
            userOnline.setTimeout(timeout);
            list.add(userOnline);
        }
        return list;
    }

    @Override
    public boolean forceLogout(String sessionId) {
        Session session = sessionDAO.readSession(sessionId);
        session.setTimeout(0);
        session.stop();
        sessionDAO.delete(session);
        return true;
    }*/
}
