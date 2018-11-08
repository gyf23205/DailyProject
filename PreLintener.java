package guoyifan.advancedStudentSystem.beans;

import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.chrono.ThaiBuddhistEra;

public class PreLintener implements ActionListener {
	TextField name;
	TextField id;
	TextField grade;
	
	public PreLintener(TextField name,TextField id,TextField grade) {
		this.name=name;
		this.id=id;
		this.grade=grade;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(Storage.point>-1) {
			Storage.point=Storage.point+1<Storage.dbs.size()?Storage.point+1:Storage.dbs.size()-1;
			name.setText(Storage.dbs.get(Storage.point).getName());
			id.setText(Storage.dbs.get(Storage.point).getId());
			grade.setText(String.valueOf(Storage.dbs.get(Storage.point).getGrade()));
			
		}
	}

}
