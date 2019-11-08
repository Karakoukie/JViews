/*
 * Copyright (C) 2019 Tristan Muller (tristan.muller@cirad.fr)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package github.karakoukie.jviews;

import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * 
 * @since 8 nov. 2019
 * @author Tristan Muller (tristan.muller@cirad.fr)
 */
public class ElementStepPanel extends StepPanel {

    /* ------------------------------------------------------------------ */
    /* Fields                                                             */
    /* ------------------------------------------------------------------ */

    private final JTextField nameField;
    private final JTextArea descArea;
    private final JScrollPane scrollPane;
    
    /* ------------------------------------------------------------------ */
    /* Constructors                                                       */
    /* ------------------------------------------------------------------ */

    public ElementStepPanel() {
        this("Naming");
    }
    
    public ElementStepPanel(final String title) {
        super(title, 700, false);
        this.nameField = new JTextField();
        this.descArea = new JTextArea();
        this.descArea.setLineWrap(true);
        this.scrollPane = new JScrollPane(descArea, 
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.scrollPane.setPreferredSize(new Dimension(500, 100));
                
        getControlPanel().addLine("Name", nameField);
        getControlPanel().addLine("Description", scrollPane);
        
        addCheckFieldValidityCallback(() -> {
            if (nameField.getText().isEmpty()) {
                return "The name value cannot be empty";
            }
            
            return null;
        });
    }
    
    /* ------------------------------------------------------------------ */
    /* Methods                                                            */
    /* ------------------------------------------------------------------ */

    public final void setNameValue(final String value) {
        nameField.setName(value);
    }
    
    public final void setDescriptionValue(final String value) {
        descArea.setName(value);
    }
    
    public final String getNameValue() {
        return nameField.getText();
    }
    
    public final String getDescriptionValue() {
        return descArea.getText();
    }
    
    /* ------------------------------------------------------------------ */
    /* Overriden methods                                                  */
    /* ------------------------------------------------------------------ */

    /* ------------------------------------------------------------------ */
    /* Setteurs                                                           */
    /* ------------------------------------------------------------------ */

    /* ------------------------------------------------------------------ */
    /* Getteurs                                                           */
    /* ------------------------------------------------------------------ */

    /* ------------------------------------------------------------------ */
    /* Others methods                                                     */
    /* ------------------------------------------------------------------ */

}
