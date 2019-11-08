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

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @since 8 nov. 2019
 * @author Tristan Muller (tristan.muller@cirad.fr)
 */
public class StepPanel extends JPanel {

    /* ------------------------------------------------------------------ */
    /* Fields                                                             */
    /* ------------------------------------------------------------------ */

    private final String title;
    private final MarginPanel controlCardPanel;
    private final ControlPanel controlPanel;
    private final boolean mandatory;
    private final MarginPanel errorCardPanel;
    private final TitledPanel errorTitledPanel;
    private final JLabel errorLabel;
    private final List<Callable<String>> checkFieldValidityCallbacks;
    
    /* ------------------------------------------------------------------ */
    /* Constructors                                                       */
    /* ------------------------------------------------------------------ */

    public StepPanel(final String title, final int lineWidth, 
            final boolean mandatory) {
        super();
        this.title = title;
        this.controlCardPanel = new MarginPanel();
        this.controlPanel = new ControlPanel(title, lineWidth);
        this.mandatory = mandatory;
        this.errorCardPanel = new MarginPanel();
        this.errorTitledPanel = new TitledPanel(null);
        this.errorTitledPanel.setPreferredSize(new Dimension(lineWidth, 35));
        this.errorLabel = new JLabel();
        this.errorLabel.setIcon(new ImageIcon(getClass().
                getResource("/icons/dialog-error.png")));
        this.errorLabel.setVisible(false);
        this.checkFieldValidityCallbacks = new ArrayList();
        
        this.setLayout(new BorderLayout(0, 0));
        this.controlCardPanel.add(controlPanel);
        this.add(controlCardPanel, BorderLayout.CENTER);
        this.errorCardPanel.add(errorTitledPanel);
        this.errorTitledPanel.getContentPanel().add(errorLabel);
        this.add(errorCardPanel, BorderLayout.SOUTH);
    }
    
    /* ------------------------------------------------------------------ */
    /* Methods                                                            */
    /* ------------------------------------------------------------------ */
    
    public final void addCheckFieldValidityCallback(
            final Callable<String> callback) {
        this.checkFieldValidityCallbacks.add(callback);
    }
    
    public final boolean checkFieldsValidity() {
        boolean validity = true;
        int errorCount = 0;
                
        for (Callable<String> callback : checkFieldValidityCallbacks) {
            try {
                final String error = callback.call();
                
                if (error != null) {
                    validity = false;
                    errorCount++;
                    
                    if (errorCount >= 2) {
                        this.errorLabel.setText("There is many errors !");
                    } else {
                        this.errorLabel.setText(error);
                    }
                }
            } catch (Exception ex) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        this.errorLabel.setVisible(!validity);
        
        return validity;
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

    public final String getTitle() {
        return title;
    }
    
    public final ControlPanel getControlPanel() {
        return controlPanel;
    }
    
    public final boolean isMandatory() {
        return mandatory;
    }
    
    /* ------------------------------------------------------------------ */
    /* Others methods                                                     */
    /* ------------------------------------------------------------------ */

}
