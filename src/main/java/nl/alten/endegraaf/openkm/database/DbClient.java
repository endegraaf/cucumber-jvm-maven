package nl.alten.endegraaf.openkm.database;

//import com.openkm.api.OKMFolder;
//import com.openkm.dao.bean.NodeFolder;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class DbClient {

    Session session = null;
    boolean found = true;

    public void removeAllSubFoldersFromRoot() {

        //TODO: https://docs.openkm.com/kcenter/view/okm-6.3-com/script-purge-all-folders-into-some-parent-.html
        Session HibernateUtil = null;
        try {
            String sql = "from NodeFolder nf where nf.parent='aa08b392-0117-4b29-af9a-c4419ba6cab6'";
            while (found) {
                session = HibernateUtil.getSessionFactory().openSession();
                Query q = session.createQuery(sql);
                q.setMaxResults(100);
                found = false;
                List fldList = new ArrayList();

//                for (NodeFolder fld : q.list()) {
//                    found= true;
//                    fldList.add(fld.getUuid());
//                }

//                for (String uuid : fldList) {
//                    try {
//                        OKMFolder.getInstance().purge(null, uuid);
//                    } catch (Exception e) {
//                        System.out.println(e.getMessage() + "<br/>");
//                    }
//                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            session.close();

        }

    }

}
