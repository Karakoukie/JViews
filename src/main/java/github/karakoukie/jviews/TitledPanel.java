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
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/**
 *
 * @since 8 nov. 2019
 * @author Tristan Muller (tristan.muller@cirad.fr)
 */
public class TitledPanel extends JPanel {

    /* ------------------------------------------------------------------ */
    /* Fields                                                             */
    /* ------------------------------------------------------------------ */

    private final JLabel titleLabel;
    private final JPanel headerPanel;
    private final JPanel contentPanel;
    
    /* ------------------------------------------------------------------ */
    /* Constructors                                                       */
    /* ------------------------------------------------------------------ */

    public TitledPanel(final String title) {
        super();
        this.titleLabel = new JLabel(title);
        this.titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD));
        this.titleLabel.setForeground(Color.WHITE);
        this.headerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        this.headerPanel.setBackground(new Color(37, 93, 140));
        this.headerPanel.setBorder(new LineBorder(new Color(37, 93, 140)));
        this.headerPanel.add(titleLabel);
        this.contentPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        this.contentPanel.setBackground(new Color(240, 240, 240));
        this.contentPanel.setBorder(new LineBorder(new Color(37, 93, 140)));
        
        this.setLayout(new BorderLayout());
        
        if (title != null) {
            this.add(headerPanel, BorderLayout.NORTH);
        }
        
        this.add(contentPanel, BorderLayout.CENTER);
    }
    
    /* ------------------------------------------------------------------ */
    /* Methods                                                            */
    /* ------------------------------------------------------------------ */

    /* ------------------------------------------------------------------ */
    /* Overriden methods                                                  */
    /* ------------------------------------------------------------------ */
    
    /* ------------------------------------------------------------------ */
    /* Setteurs                                                           */
    /* ------------------------------------------------------------------ */

    /* ------------------------------------------------------------------ */
    /* Getteurs                                                           */
    /* ------------------------------------------------------------------ */

    public final JPanel getContentPanel() {
        return contentPanel;
    }
    
    /* ------------------------------------------------------------------ */
    /* Others methods                                                     */
    /* ------------------------------------------------------------------ */

}
