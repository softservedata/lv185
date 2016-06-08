package com.softserve.edu.hb.Entity;

public class CompetitionsDB implements IEntity{
	   public static enum UserDBQueries {
	        INSERT(DaoQueries.INSERT, "INSERT INTO competitions (id, name, description, start,end,IdCreteria) VALUES (%s, '%s', '%s', '%s', '%s', %s);"),
	        GET_BY_ID(DaoQueries.GET_BY_ID, "SELECT id, name, description, start,end,IdCreteria FROM users WHERE id = %s;"),
	        GET_BY_FIELD(DaoQueries.GET_BY_FIELD, "SELECT id, name, description, start,end,IdCreteria FROM users WHERE %s = '%s';"),
	        GET_ALL(DaoQueries.GET_ALL, "SELECT id, name, description, start,end,IdCreteria FROM competitions;"),
	        UPDATE_BY_FIELD(DaoQueries.UPDATE_BY_FIELD, "UPDATE competitions SET %s = '%s' WHERE %s = '%s';"),
	        DELETE_BY_ID(DaoQueries.DELETE_BY_ID, "DELETE competitions WHERE id = %s;"),
	        DELETE_BY_FIELD(DaoQueries.DELETE_BY_FIELD, "DELETE competitions WHERE %s = '%s';");
	        
		   //DELETE_USER_BY_PARTIAL_LOGIN("DELETE dbo.Users WHERE Login LIKE '%s%%';");
	        private DaoQueries daoQuery;
	        private String query;

	        private UserDBQueries(DaoQueries daoQuery, String query) {
	        	this.daoQuery = daoQuery;
	            this.query = query;
	        }

	        public DaoQueries getDaoQuery() {
	            return daoQuery;
	        }

	        @Override
	        public String toString() {
	            return query;
	        }
	    }
	   
	    private Integer id;
	    private Integer IdCreteria;
	    private String name;
	    private String description;
	    private String start;
	    private String end;
		
	    // TODO Create Factory, Builder   

	    

		public CompetitionsDB(Integer id, Integer idCreteria, String name, String description, String start,
				String end) {
			super();
			this.id = id;
			IdCreteria = idCreteria;
			this.name = name;
			this.description = description;
			this.start = start;
			this.end = end;
		}
		
	    
	    //	    getters and setters
	    public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public Integer getIdCreteria() {
			return IdCreteria;
		}
		public void setIdCreteria(Integer idCreteria) {
			IdCreteria = idCreteria;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public String getStart() {
			return start;
		}
		public void setStart(String start) {
			this.start = start;
		}
		public String getEnd() {
			return end;
		}
		public void setEnd(String end) {
			this.end = end;
	}
}
		
	    


