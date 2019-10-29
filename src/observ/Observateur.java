package observ;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
public class Observateur {
	interface IObservateur
	{
		public void notifier();
	}

	class Observable
	{

		public Observable() 
		{
			m_observateurs = new LinkedList<IObservateur>();
		}
		
		public void notifierObservateurs()
		{
		   Iterator<IObservateur> it = m_observateurs.iterator();
		    // Notifier tous les observerateurs
		   while(it.hasNext()){
			   IObservateur obs = it.next();
			   obs.notifier();
		   }
		}
		
		void ajouterObservateur(IObservateur observateur)
		{
		    // On ajoute un changement de temperature
		    m_observateurs.add(observateur);
		}
		
		void supprimerObservateur(IObservateur observateur)
		{
		    // Enlever un changement dans le climat
		    m_observateurs.remove(observateur);
		}
		
		private List<IObservateur> m_observateurs;
	}

	class Capteur implements IObservateur
	{
		public void notifier()
		{
			System.out.println("Capteur a recu la notif");
			// Changement d'état.
		}
	}

	class Moteur implements IObservateur
	{
		public void notifier()
		{
			 System.out.println("variation du climat");
			 
		}
	}

	class CapteurLaser extends Observable
	{
	   //  boucle while
		public void run()
		{
			while(true)
			{
				if(m_detecteVariation)
					notifierObservateurs();
			}
		}

		private boolean m_detecteVariation;
	}


	public  class Run {

		/**
		 * @param args
		 */
		public  void main(String[] args) 
		{
			Capteur instanceCabine = new Capteur();
			Moteur instanceMoteur = new Moteur();
			
			CapteurLaser capteurtemp = new CapteurLaser();;
			
			capteurtemp.ajouterObservateur(instanceCabine);
			capteurtemp.ajouterObservateur(instanceMoteur);
			
			// On simule manuellement une variation (normalement c'est le thread qui s'en charge)
			capteurtemp.notifierObservateurs();
			
			// La cabine et le moteur ont reçu une notification sur leur méthode notifier()
			
			capteurtemp.supprimerObservateur(instanceMoteur);
			System.out.println("Suppression du moteur dans les abonnes");
			
			capteurtemp.notifierObservateurs();
		}
	}	

}
