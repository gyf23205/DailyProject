package guoyifan.advancedStudentSystem.beans;

import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BackListener implements ActionListener {
	TextField name;
	TextField id;
	TextField grade;
	public BackListener(TextField name,TextField id,TextField grade) {
		this.name=name;
		this.id=id;
		this.grade=grade;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(Storage.point>-1) {
			Storage.point=Storage.point-1>0?Storage.point-1:0;
			name.setText(Storage.dbs.get(Storage.point).getName());
			id.setText(Storage.dbs.get(Storage.point).getId());
			grade.setText(String.valueOf(Storage.dbs.get(Storage.point).getGrade()));
		}	
	}

}
