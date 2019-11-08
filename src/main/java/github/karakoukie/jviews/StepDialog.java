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
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @since 7 nov. 2019
 * @author Tristan Muller (tristan.muller@cirad.fr)
 */
public class StepDialog extends JDialog {

    // ---------------------------------------------------------------------
    // Fields                                                             
    // ---------------------------------------------------------------------
    
    private final MarginPanel headerPanel;
    private final TitledPanel stepsPanel;
    private final JPanel bodyPanel;
    private final NavigationPanel navigationPanel;

    private final List<StepPanel> steps;
    private int currentStep;
    private Runnable finishCallback;

    // ---------------------------------------------------------------------
    // Constructors
    // ---------------------------------------------------------------------
    
    public StepDialog(final String title, final JFrame owner) {
        super(owner, true);
        this.setTitle(title);
        this.setResizable(false);

        this.headerPanel = new MarginPanel();
        this.stepsPanel = new TitledPanel("Steps");
        this.bodyPanel = new JPanel(new CardLayout());
        this.navigationPanel = new NavigationPanel();

        this.getContentPane().setLayout(new BorderLayout());
        this.headerPanel.add(stepsPanel);
        this.getContentPane().add(headerPanel, BorderLayout.WEST);
        this.getContentPane().add(bodyPanel, BorderLayout.CENTER);
        this.getContentPane().add(navigationPanel, BorderLayout.SOUTH);

        this.steps = new ArrayList();
        this.currentStep = 0;

        this.navigationPanel.addBackEvent(() -> {
            if (currentStep > 0) {
                currentStep--;
                updateSteps();
            }
        });

        this.navigationPanel.addNextEvent(() -> {
            if (steps.get(currentStep).checkFieldsValidity()) {
                if (currentStep < steps.size()) {
                    currentStep++;
                    updateSteps();
                }
            }
        });

        this.navigationPanel.addFinishEvent(() -> {
            if (steps.get(currentStep).checkFieldsValidity()) {
                if (finishCallback != null) {
                    finishCallback.run();
                }

                dispose();
            }
        });

        this.navigationPanel.addCancelEvent(() -> {
            dispose();
        });
    }

    // ---------------------------------------------------------------------
    // Methods
    // ---------------------------------------------------------------------
    
    public final void addStep(final StepPanel panel) {
        if (panel != null) {
            if (panel.isMandatory()) {
                steps.add(0, panel);
            } else {
                steps.add(panel);
            }

            updateSteps();
        }
    }

    public final void setFinishCallback(final Runnable callback) {
        this.finishCallback = callback;
    }

    private void updateSteps() {
        if (currentStep >= steps.size() - 1) {
            navigationPanel.enableNextButton(false);
            navigationPanel.enableFinishButton(true);
        } else {
            navigationPanel.enableNextButton(true);

            if (steps.get(currentStep + 1).isMandatory()) {
                navigationPanel.enableFinishButton(false);
            } else {
                navigationPanel.enableFinishButton(true);
            }
        }

        if (currentStep <= 0) {
            navigationPanel.enableBackButton(false);
        } else {
            navigationPanel.enableBackButton(true);
        }

        stepsPanel.getContentPanel().removeAll();
        bodyPanel.removeAll();

        if (!steps.isEmpty()) {
            for (int i = 0; i < steps.size(); i++) {
                final JLabel label = new JLabel((i + 1) + ". "
                        + steps.get(i).getTitle());
                label.setPreferredSize(new Dimension(150, 17));

                if (i == currentStep) {
                    label.setFont(label.getFont().deriveFont(Font.BOLD));
                }

                stepsPanel.setPreferredSize(new Dimension(200, 0));
                stepsPanel.getContentPanel().add(label);
            }

            bodyPanel.add(steps.get(currentStep));
        }

        pack();
        repaint();
        setLocationRelativeTo(getParent());
    }

    // ---------------------------------------------------------------------
    // Overriden methods
    // ---------------------------------------------------------------------

    // ---------------------------------------------------------------------
    // Setteurs
    // ---------------------------------------------------------------------

    // ---------------------------------------------------------------------
    // Getteurs
    // ---------------------------------------------------------------------

    // ---------------------------------------------------------------------
    // Others methods
    // ---------------------------------------------------------------------
    
    public static final void main(String[] args) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        final StepDialog dialog = new StepDialog("Step dialog", null);
        dialog.addStep(new NodeStepPanel(new String[] { "Root" }));
        dialog.setVisible(true);
    }

}
