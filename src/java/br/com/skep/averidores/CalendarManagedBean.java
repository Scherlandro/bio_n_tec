/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.skep.averidores;

import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.event.SelectEvent;

@ManagedBean
@SessionScoped
public class CalendarManagedBean {
	private Date date = new Date();

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public void dateSelectedAction(SelectEvent e){
		Date date = (Date)e.getObject();
		System.out.println("Date Selected Is ::"+date);
	}
		
	
}

