<%@page import="java.util.Arrays"%>
<%@page import="java.util.Comparator"%>
<%@page import="com.gestionecorsi.businesscomponent.model.Docente"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.gestionecorsi.businesscomponent.model.Corso"%>
<%@page import="com.gestionecorsi.businesscomponent.model.Corsista"%>
<%@page import="com.gestionecorsi.businesscomponent.AdminFacade"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@ include file="cdn.html"%>
<link rel="stylesheet" href="css/general.css">
<link rel="stylesheet" href="css/footer.css">
<link rel="stylesheet" href="css/navbar.css">
<link rel="stylesheet" href="css/statistiche.css">
<title>Statistiche</title>
</head>
<body>
	<jsp:include page="navbar.jsp" />
	<div class="container">
		<div class="page-header">
			<h3>Statistiche</h3>
		</div>

		<div class="flex-evenly">
			<!-- Dati sui corsi -->
			<!-- TODO dividere in due card: corsi e docenti -->
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">Dati sui corsi</h3>
				</div>
				<div class="panel-body">
					<!-- TODO convertire in tabella -->
					<div class="flex-between">
						<span>Corso pi&ugrave; frequentato:</span>
						<%
						Corso cpf = AdminFacade.getInstance().getCorsoPiuFrequentato();
						if (cpf != null) {
							int iscritti = AdminFacade.getInstance().getIscrittiCorso(cpf.getCodCorso()).length;
						%>
						<span data-toggle="tooltip" data-placement="top" data-html="true"
							title='Iscritti: <span class="badge"><%=iscritti%></span>'><%=cpf.getNomeCorso()%></span>
						<%
						} else {
						%>
						<span>---</span>
						<%
						}
						%>
					</div>

					<div class="flex-between">
						<span>Inizio ultimo corso:</span>
						<%
						Corso uc = AdminFacade.getInstance().getUltimoCorso();
						if (uc != null) {
						%>
						<span data-toggle="tooltip" data-placement="top"
							title="<%=uc.getNomeCorso()%>"><%=new SimpleDateFormat("dd/MM/yyyy").format(uc.getDataInizioCorso())%></span>
						<%
						} else {
						%>
						<span>---</span>
						<%
						}
						%>
					</div>

					<div class="flex-between">
						<span>Durata media (giorni lavorativi):</span>
						<span class="badge"><%=String.format("%.1f", AdminFacade.getInstance().getDurataMedia())%></span>
					</div>

					<div class="flex-between">
						<span>Commenti presenti:</span>
						<span class="badge"><%=AdminFacade.getInstance().getNumeroCommenti()%></span>
					</div>
				</div>
			</div>

			<!-- Dati sui docenti -->
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">Dati sui docenti</h3>
				</div>
				<div class="panel-body">
					<table class="table table-hover">
						<tbody>
							<tr>
								<th class="th-fit-width text-right">Docente con pi&ugrave;
									abilit&agrave;</th>
								<td>
									<%
									Docente dpc = AdminFacade.getInstance().getDocentePiuCorsi();
									if (dpc != null) {
									%>
									<span id="tooltip-CV" data-toggle="tooltip" data-placement="bottom" data-html="true" title="<h4>CV</h4><ul>
										<%
										for (String li : dpc.getCvDocente().split(",( *)")) {
										%>
											<li><%=li%></li>
										<%
										}
										%>
										</ul>">
										<%=dpc.getNomeDocente() + " " + dpc.getCognomeDocente()%>
									</span>
									<%
									} else {
									%>
									Nessun docente registrato.
									<%
									}
									%>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>

		<!-- Tabella corsi disponibili -->
		<div class="panel panel-default panel-center">
			<%
			Corso[] corsi = AdminFacade.getInstance().getCorsiDisponibili();
			/* Ordinati per data di inizio */
			Arrays.sort(corsi, new Comparator<Corso>() {
				@Override
				public int compare(Corso c1, Corso c2) {
					return c1.getDataInizioCorso().compareTo(c2.getDataInizioCorso());
				}
			});
			%>
			<div class="panel-heading panel-heading-pill">
				<h3 class="panel-title">Corsi disponibili</h3>
				<span class="badge"><%=corsi.length%></span>
			</div>
			<div class="panel-body">
				<%
				if (corsi.length > 0) {
				%>
				<div class="table-responsive">
					<table class="table table-hover">
						<thead>
							<tr>
								<th>Corso</th>
								<th>Inizio</th>
								<th>Fine</th>
								<th class="th-fit-width">Posti</th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<%
							for (int i = 0; i < corsi.length; i++) {
								Docente docente = AdminFacade.getInstance().getDocenteByID(corsi[i].getCodDocente());
								byte posti = (byte) (12 - AdminFacade.getInstance().getIscrittiCorso(corsi[i].getCodCorso()).length);
							%>
							<tr>
								<td><%=corsi[i].getNomeCorso()%></td>
								<td><%=new SimpleDateFormat("dd/MM/yyyy").format(corsi[i].getDataInizioCorso())%></td>
								<td><%=new SimpleDateFormat("dd/MM/yyyy").format(corsi[i].getDataFineCorso())%></td>
								<td class="text-right"><%=posti%></td>
								<td>
									<div class="text-right">
										<a class="btn btn-xs btn-default" href="#" data-toggle="modal"
											data-target="#dettagliCorso_<%=i%>">Dettagli</a>
									</div>
									
									<!-- Modale dettagli corso -->
									<div class="modal fade" id="dettagliCorso_<%=i%>" role="dialog">
										<div class="modal-dialog modal-md" role="dialog">
											<div class="modal-content">
												<div class="modal-header">
													<button type="button" class="close" data-dismiss="modal">&times;</button>
													<h3 class="modal-title">Dettagli corso</h3>
												</div>
												<div class="modal-body">
													<table class="table table-hover">
														<tbody>
															<tr>
																<th class="th-fit-width text-right">Nome</th>
																<td><%=corsi[i].getNomeCorso()%></td>
															</tr>
															<tr>
																<th class="th-fit-width text-right">Docente</th>
																<td><%=docente.getNomeDocente() + " " + docente.getCognomeDocente()%></td>
															</tr>
															<tr>
																<th class="th-fit-width text-right">Aula</th>
																<td><%=corsi[i].getAulaCorso()%></td>
															</tr>
															<tr>
																<th class="th-fit-width text-right">Data inizio</th>
																<td><%=new SimpleDateFormat("dd/MM/yyyy").format(corsi[i].getDataInizioCorso())%></td>
															</tr>
															<tr>
																<th class="th-fit-width text-right">Data fine</th>
																<td><%=new SimpleDateFormat("dd/MM/yyyy").format(corsi[i].getDataFineCorso())%></td>
															</tr>
															<tr>
																<th class="th-fit-width text-right">Posti
																	disponibili</th>
																<td><%=posti%></td>
															</tr>
															<tr>
																<th class="th-fit-width text-right">Costo</th>
																<td>&euro;&nbsp;<%=corsi[i].getCostoCorso()%></td>
															</tr>
															<tr>
																<th class="th-fit-width text-right">Commenti</th>
																<%
																if (corsi[i].getCommentiCorso() != null && !corsi[i].getCommentiCorso().trim().equals("")) {
																%>
																<td><%=corsi[i].getCommentiCorso()%></td>
																<%
																} else {
																%>
																<td>Nessun commento presente.</td>
																<%
																}
																%>
															</tr>
														</tbody>
													</table>
												</div>
												<div class="modal-footer">
													<button type="button" class="btn btn-primary"
														data-dismiss="modal">Chiudi</button>
												</div>
											</div>
										</div>
									</div>
								</td>
							</tr>
							<%
							}
							%>
						</tbody>
					</table>
				</div>
				<%
				} else {
				%>
				<p>Nessun corso con posti disponibili.</p>
				<%
				}
				%>
			</div>
		</div>


		<!-- Tabella corsisti -->
		<div class="panel panel-default panel-center">
			<%
			Corsista[] corsisti = AdminFacade.getInstance().getCorsisti();
			%>
			<div class="panel-heading panel-heading-pill">
				<h3 class="panel-title">Studenti</h3>
				<span class="badge"><%=corsisti.length%></span>
			</div>
			<div class="panel-body">
				<div class="table-responsive">
					<table class="table table-hover">
						<thead>
							<tr>
								<th>Nome</th>
								<th>Cognome</th>
								<th class="th-fit-width">Precedenti formativi</th>
							</tr>
						</thead>
						<tbody>
							<%
							for (int i = 0; i < corsisti.length; i++) {
							%>
							<tr>
								<td><%=corsisti[i].getNomeCorsista()%></td>
								<td><%=corsisti[i].getCognomeCorsista()%></td>
								<td style="text-align: center; max-width: 5em;">
									<%
									String iconClass;
									if (corsisti[i].getPrecedentiFormativi())
										iconClass = "glyphicon glyphicon-ok";
									else
										iconClass = "glyphicon glyphicon-remove";
									%> <i class="<%=iconClass%>"></i>
								</td>
							</tr>
							<%
							}
							%>
						</tbody>
					</table>
				</div>
			</div>
		</div>


	</div>
	<footer><%@ include file="footer.html"%></footer>

	<script type="text/javascript">
		$(function() {
			$("[data-toggle='tooltip']").tooltip();
		});
	</script>
</body>
</html>