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

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @since 8 nov. 2019
 * @author Tristan Muller (tristan.muller@cirad.fr)
 */
public class ControlPanel extends TitledPanel {

    /* ------------------------------------------------------------------ */
    /* Fields                                                             */
    /* ------------------------------------------------------------------ */

    private final int controlWidth;
    private int controlHeight;
    
    /* ------------------------------------------------------------------ */
    /* Constructors                                                       */
    /* ------------------------------------------------------------------ */

    public ControlPanel(final String title, final int controlWidth) {
        super(title);
        this.controlWidth = controlWidth;
        this.controlHeight = 60;
    }
    
    /* ------------------------------------------------------------------ */
    /* Methods                                                            */
    /* ------------------------------------------------------------------ */

    public final void addLine(final JComponent component) {
        final JPanel panel = new JPanel(new GridLayout(1, 0));
        panel.setBackground(new Color(240, 240, 240));
        panel.setPreferredSize(new Dimension(controlWidth, 
                component.getPreferredSize().height));
        controlHeight += component.getPreferredSize().height;
        
        panel.add(component);
        getContentPanel().add(panel);
        updateDimension();
    }
    
    public final void addLine(final String text, final JComponent component) {
        final JPanel panel = new JPanel(new GridLayout(1, 0));
        panel.setBackground(new Color(240, 240, 240));
        panel.setPreferredSize(new Dimension(controlWidth, 
                component.getPreferredSize().height));
        controlHeight += component.getPreferredSize().height;
        
        final JLabel label = new JLabel(text + ": ");
        label.setLabelFor(component);
        panel.add(label);
        
        panel.add(component);
        
        getContentPanel().add(panel);
        updateDimension();
    }
    
    public final void addLine(final String text, final JComponent firstComponent,
            final JComponent secondComponent) {
        final JPanel panel = new JPanel(new GridLayout(1, 0));
        panel.setBackground(new Color(240, 240, 240));
        
        int height = firstComponent.getHeight();
        
        if (firstComponent.getPreferredSize().height 
                < secondComponent.getPreferredSize().height) {
            height = secondComponent.getPreferredSize().height;
        }
        
        panel.setPreferredSize(new Dimension(controlWidth, height));
        controlHeight += height;
        
        final JLabel label = new JLabel(text + ": ");
        label.setLabelFor(firstComponent);
        panel.add(label);
        
        panel.add(firstComponent);
        panel.add(secondComponent);
        
        getContentPanel().add(panel);
        updateDimension();
    }
    
    private final void updateDimension() {
        this.setPreferredSize(new Dimension(controlWidth + 50, controlHeight));
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
