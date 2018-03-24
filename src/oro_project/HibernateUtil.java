package oro_project;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

class HibernateUtil {
	public static String pathToConfigutationFile = "oro_project/hibernate.cfg.xml";

	public static SessionFactory buildSessionFactory(){
		try {
			Configuration configuration = new Configuration();
            configuration.configure(pathToConfigutationFile);
            ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().
            		applySettings(configuration.getProperties()).buildServiceRegistry();
            return configuration.buildSessionFactory(serviceRegistry);
		}
		catch(Throwable ex){
			System.out.println();
			throw new ExceptionInInitializerError(ex);
		}
	}
}
