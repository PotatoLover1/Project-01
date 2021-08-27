
import java.sql.Date;

import com.example.dao.ReimbursementDaoHibernate;
import com.example.dao.UserDaoHibernate;
import com.example.models.Reimbursement;
import com.example.models.ReimbursementType;
import com.example.models.User;
import com.example.services.ReimbursementServices;
import com.example.services.UserServices;

public class ReimbursementDriver {
	private static UserDaoHibernate uDao = new UserDaoHibernate();
	private static ReimbursementDaoHibernate rDao = new ReimbursementDaoHibernate();
	private static UserServices uServ = new UserServices(uDao);
	private static ReimbursementServices rServ = new ReimbursementServices(rDao);
	
	public static void main(String[] args) {
		 User u = uServ.getUserById(4);
		 
		 ReimbursementType t = rDao.getTypeById(2);
		 Reimbursement r = new Reimbursement(u,t, new Date(System.currentTimeMillis()),"took a Trip", 50.50);
		 rDao.createReimbursement(r);
	}
}
