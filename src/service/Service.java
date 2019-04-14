package service;

public abstract class Service {

	protected boolean daoAvailable = true;
	
	public Service() {
		InstantiateDAO();
	}
	
	protected abstract boolean InstantiateDAO();
	
}
