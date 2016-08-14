package common;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Note;

@SuppressWarnings("serial")
public class List_noteServlet extends HttpServlet{
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = request.getParameter("method");
		if("createForm".equals(method)){
			createForm(request,response);
		}else if("editForm".equals(method)){
			editForm(request,response);
		}else if("save".equals(method)){
			save(request,response);
			search(request,response);
		}else if("delete".equals(method)){
			delete(request,response);
			search(request,response);
		}else{
			search(request,response);
		}
	}

	void search(HttpServletRequest request,HttpServletResponse response)
			throws ServletException,IOException{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			String sql=null;
			String keyword=request.getParameter("keyword");
			if(keyword==null){
				sql="select id,name,type,province,city,linkman,remark,person,date from client";
			}else{
				sql="select id,name,type,province,city,linkman,remark,person,date from client where name"
					+ " like ? or type like ? or province like ? or city like ? or linkman like ? or remark like ? or person like ? or date like ?";
	         }
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			//如果存在查询内容，需要设置查询条件 
			if(keyword!=null){
				pstmt.setString(1, "%"+keyword+"%");
				pstmt.setString(2, "%"+keyword+"%");
				pstmt.setString(3, "%"+keyword+"%");
				pstmt.setString(4, "%"+keyword+"%");
				pstmt.setString(5, "%"+keyword+"%");
				pstmt.setString(6, "%"+keyword+"%");
				pstmt.setString(7, "%"+keyword+"%");
				pstmt.setString(8, "%"+keyword+"%");
			}
			rs = pstmt.executeQuery();
			List<Note> list=new ArrayList<Note>();
			while(rs.next()){
				Note note = rsToNote(rs);
				list.add(note);
			}
			request.setAttribute("notes", list);
			request.getRequestDispatcher("/list_notes.jsp").forward(request, response);
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		}finally{
			close(conn, pstmt, rs);
		}
	}
	
	void createForm(HttpServletRequest request,HttpServletResponse response)throws 
    ServletException,IOException{
    	request.getRequestDispatcher("/insert.jsp").forward(request, response);
    	
    }

	void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			conn = getConnection();
			String sql = "delete from client where id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		}finally{
			close(conn, pstmt);
		}
	}
	
	void editForm(HttpServletRequest request,HttpServletResponse response)throws
    ServletException, IOException{
    	Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			int id = Integer.parseInt(request.getParameter("id"));
			String sql="select * from client where id="+id;
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			Note note = rsToNote(rs);
			request.setAttribute("note", note);
			request.getRequestDispatcher("/insert.jsp").forward(request, response);
		}catch(SQLException e){
			throw new RuntimeException(e.getMessage(),e);
		}finally{
			if(rs != null)
				try {
					rs.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			if(pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			if(conn != null)
				try {
					conn.close();
				} catch (SQLException e3) {
					e3.printStackTrace();
				}
		}
    }


	void save(HttpServletRequest request,HttpServletResponse response)throws
	ServletException, IOException{
		String id = request.getParameter("id");
		if(id != null && !"".equals(id)){
			update(request,response);
		}else{
			insert(request,response);
		}
	}
	
	void insert(HttpServletRequest request, HttpServletResponse response) throws
	ServletException, IOException{
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			String name=request.getParameter("name");
			String type=request.getParameter("type");
			String province=request.getParameter("province");
			String city=request.getParameter("city");
			String linkman=request.getParameter("linkman");
			String remark=request.getParameter("remark");
			String person=request.getParameter("person");
			String date=(new java.util.Date()).toLocaleString();
			String sql="insert into client(name,type,province,city,linkman,remark,person,date)values(?,?,?,?,?,?,?,?)";
			conn=getConnection();
			pstmt = conn.prepareStatement(sql);
				pstmt.setString(1,name);
				pstmt.setString(2,type);
				pstmt.setString(3,province);
				pstmt.setString(4,city);
				pstmt.setString(5,linkman);
				pstmt.setString(6,remark);
				pstmt.setString(7,person);
				pstmt.setString(8,date);
				pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		}finally{
			if(pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if(conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		
		
	}

	void update(HttpServletRequest request, HttpServletResponse response) throws
    ServletException, IOException{
    	Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			String name=request.getParameter("name");
			String type=request.getParameter("type");
			String province=request.getParameter("province");
			String city=request.getParameter("city");
			String linkman=request.getParameter("linkman");
			String remark=request.getParameter("remark");
			String person=request.getParameter("person");
			String date=(new java.util.Date()).toLocaleString();
			conn = getConnection();
			String sql="update client set name=?,type=?,province=?,city=?,linkman=?,remark=?,person=?,date=? where id=?";
			pstmt = conn.prepareStatement(sql);
				pstmt.setString(1,name);
				pstmt.setString(2,type);
				pstmt.setString(3,province);
				pstmt.setString(4,city);
				pstmt.setString(5,linkman);
				pstmt.setString(6,remark);
				pstmt.setString(7,person);
				pstmt.setString(8,date);
				pstmt.setInt(9, id);
				pstmt.executeUpdate();	
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		}finally{
			if(pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if(conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		
	}

	Connection getConnection() {
		try{
			@SuppressWarnings("unused")
			String DriverName = "com.mysql.jdbc.Driver";
			String url="jdbc:mysql://localhost/dls_crm?useUnicode=true&characterEncoding=utf-8";
			String user="root";
			String password="root";
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection conn=DriverManager.getConnection(url, user, password);
			return conn;
			
		}catch(Exception e){
			throw new RuntimeException(e.getMessage(),e);
		}
	}
	
	void close(Connection conn,PreparedStatement pstmt){
		if(pstmt != null)
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		if(conn != null)
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	void close(Connection conn,PreparedStatement pstmt,ResultSet rs){
		if (rs!=null)
		try{
			rs.close();
			
		}catch(SQLException e1){
			e1.printStackTrace();
		}
		if(pstmt != null)
			try{
				pstmt.close();
			}catch(SQLException e2){
				e2.printStackTrace();
			}
		if(conn != null)
			try{
				conn.close();
			}catch(SQLException e3){
				e3.printStackTrace();
			}
	}
	
	Note rsToNote(ResultSet rs){
		Note note = new Note();
		try{
			note.setId(rs.getInt("id"));
			note.setName(rs.getString("name"));
			note.setType(rs.getString("type"));
			note.setProvince(rs.getString("province"));
			note.setCity(rs.getString("city"));
			note.setLinkman(rs.getString("linkman"));
			note.setRemark(rs.getString("remark"));
			note.setPerson(rs.getString("person"));
			note.setDate(rs.getString("date"));
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		}
		return note;
		}
}
