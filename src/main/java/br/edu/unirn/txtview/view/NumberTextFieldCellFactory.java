package br.edu.unirn.txtview.view;

import java.util.Locale;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;
import javafx.util.converter.NumberStringConverter;

/**
 * CellFactory para criar um TableCell que só aceite números, através do conversor {@link NumberStringConverter}.<br/>
 * Pode ser utilizado diretamente no arquivo FXML.
 * @author Reinaldo
 *
 * @param <S>
 */
public class NumberTextFieldCellFactory<S> {

	public static <S> Callback<TableColumn<S, Number>, TableCell<S, Number>> forTableColumn() {
		return TextFieldTableCell.forTableColumn(new NumberStringConverter(new Locale("pt", "br")));
	}
}
