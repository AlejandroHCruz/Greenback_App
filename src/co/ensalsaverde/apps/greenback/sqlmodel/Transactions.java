package co.ensalsaverde.apps.greenback.sqlmodel;

public class Transactions {

	int id_trans;
	String note;
	int status;
	String created_at;

	// constructors
	public Transactions() {
	}

	public Transactions(String note, int status) {
		this.note = note;
		this.status = status;
	}

	public Transactions(int id_trans, String note, int status) {
		this.id_trans = id_trans;
		this.note = note;
		this.status = status;
	}

	// setters
	public void setId(int id_trans) {
		this.id_trans = id_trans;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public void setCreatedAt(String created_at) {
		this.created_at = created_at;
	}

	// getters
	public long getId() {
		return this.id_trans;
	}

	public String getNote() {
		return this.note;
	}

	public int getStatus() {
		return this.status;
	}
	
	public String getDateTime() {
		return this.created_at;
	}
}