package racine.test.admin;

import org.springframework.stereotype.Service;
import racine.test.admin.AdminRepository;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    private  final AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }






}
