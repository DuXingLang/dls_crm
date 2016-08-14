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

import model.Link;

@SuppressWarnings("serial")
public class Link_noteServlet extends HttpServlet{
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
				sql="select id,name,phoneNumber,qq,email,company,remark,date from link";
			}else{
				sql="select id,name,phoneNumber,qq,email,company,remark,date from link where name"
					+ " like ? or phoneNumber like ? or qq like ? or email like ? or company like ? or remark like ? or date like ?";
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
			}
			rs = pstmt.executeQuery();
			List<Link> list=new ArrayList<Link>();
			while(rs.next()){
				Link note = rsToNote(rs);
				list.add(note);
			}
			request.setAttribute("notes", list);
			request.getRequestDispatcher("/link_notes.jsp").forward(request, response);
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		}finally{
			close(conn, pstmt, rs);
		}
	}
	
	void createForm(HttpServletRequest request,HttpServletResponse response)throws 
    ServletException,IOException{
    	request.getRequestDispatcher("/link_insert.jsp").forward(request, response);
    	
    }

	void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			conn = getConnection();
			String sql = "delete from link where id=?";
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
			String sql="select * from link where id="+id;
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			Link note = rsToNote(rs);
			request.setAttribute("note", note);
			request.getRequestDispatcher("/link_insert.jsp").forward(request, response);
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
			String phoneNumber=request.getParameter("phoneNumber");
			String qq=request.getParameter("qq");
			String email=request.getParameter("email");
			String company=request.getParameter("company");
			String remark=request.getParameter("remark");
			String date=(new java.util.Date()).toLocaleString();
			String sql="insert into link(name,phoneNumber,qq,email,company,remark,date)values(?,?,?,?,?,?,?)";
			conn=getConnection();
			pstmt = conn.prepareStatement(sql);
				pstmt.setString(1,name);
				pstmt.setString(2,phoneNumber);
				pstmt.setString(3,qq);
				pstmt.setString(4,email);
				pstmt.setString(5,company);
				pstmt.setString(6,remark);
				pstmt.setString(7,date);
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
			String phoneNumber=request.getParameter("phoneNumber");
			String qq=request.getParameter("qq");
			String email=request.getParameter("email");
			String company=request.getParameter("company");
			String remark=request.getParameter("remark");
			String date=(new java.util.Date()).toLocaleString();
			conn = getConnection();
			String sql="update link set name=?,phoneNumber=?,qq=?,email=?,company=?,remark=?,date=? where id=?";
			pstmt = conn.prepareStatement(sql);
				pstmt.setString(1,name);
				pstmt.setString(2,phoneNumber);
				pstmt.setString(3,qq);
				pstmt.setString(4,email);
				pstmt.setString(5,company);
				pstmt.setString(6,remark);
				pstmt.setString(7,date);
				pstmt.setInt(8, id);
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
	
	Link rsToNote(ResultSet rs){
		Link note = new Link();
		try{
			note.setId(rs.getInt("id"));
			note.setName(rs.getString("name"));
			note.setPhoneNumber(rs.getString("phoneNumber"));
			note.setQq(rs.getString("qq"));
			note.setEmail(rs.getString("email"));
			note.setCompany(rs.getString("company"));
			note.setRemark(rs.getString("remark"));
			note.setDate(rs.getString("date"));
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		}
		return note;
		}
}



